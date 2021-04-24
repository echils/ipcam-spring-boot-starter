# IPcam Spring Boot Starter Quickstart Guide

This README.md describes how to quickly configure and use the launcher and provides a few common methods as a demonstration, other methods can be seen in the specific code  

<p align="center">
  <a>
   <img alt="Framework" src="IPCAM.PNG">
  </a>
</p>

## Development Environment  
JDK     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1.8.0_202  
Maven   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.5.4  
Spring Boot &nbsp;&nbsp;&nbsp;&nbsp;2.3.4.RELEASE  
SDK &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Library](sdk)


## Quick Start Example  

##### 1、Add the dependency to the pom.xml  
````
<dependency>
    <groupId>com.github.echils</groupId>
    <artifactId>ipcam-spring-boot-starter</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
````
##### 2、Extract the [Library](sdk) to a custom directory (take the /data directory as an example), and then add dynamic library management (take linux as an example)
````
vim /etc/profile
````
````
# Add the following parameters, modify the path according to the actual situation, and then save and exit
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/data/hikvision/linux:/data/xmeye/linux
````
````
source /etc/profile && ldconfig
````
##### 3、Implement the interface {@link ICameraSupplier } and add it to the Spring container
````
@Component
public class DefaultCameraSupplier implements ICameraSupplier {

    @Autowired
    private CameraMapper cameraMapper;
    
    /**
     * Search the database or configuration file through identification to find the basic information of the camera,
     * and package it into NetworkCamera {@link NetworkCamera}
     *
     * @param identification the unique identification of the camera
     * @return {@link NetworkCamera}
     */
    @Override
    public NetworkCamera apply(String identification) {

        Optional<Camera> optionalCamera =cameraMapper.findById(identification);
        
        if (!optionalCamera.isPresent()) {
            throw new NotFoundException();   
        }
        
        Camera camera = optionalCamera.get();
        NetworkCamera networkCamera = new NetworkCamera();
        networkCamera.setIp(camera.getIp());
        networkCamera.setPort(camera.getPort());
        networkCamera.setUsername(camera.getUsername());
        networkCamera.setPassword(camera.getPassword());
        networkCamera.setDriverType(CameraSupportedDriver.valueOf(camera.getManufacturer()));

        return networkCamera;
    }

}
````

##### 4、Autowired the pool {@link CameraConnectionPool} in your service  
````
@Service
public class Test {

    @Autowired
    private CameraConnectionPool cameraConnectionPool;

    private static final String IDENTIFICATION = "Test";
    
    public void invoke(){
    
       ICameraConnection connection = null;
       try {
       
           connection = cameraConnectionPool.borrowObject(IDENTIFICATION);
           List<String> channles = connection.getChannels();
           for (String channel : channels) {
               System.out.println(channel);
           }
       }catch (RuntimeException e) {
           throw new CameraRuntimeException();
       } finally {
           if (connection != null && connection.isConnected()) {
               cameraConnectionPool.returnObject(identification, connection);
           }
       } 
       
    }
    
}
````
