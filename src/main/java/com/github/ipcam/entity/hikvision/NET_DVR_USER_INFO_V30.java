package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

import static com.github.ipcam.entity.NetworkCameraContext.*;

public class NET_DVR_USER_INFO_V30 extends Structure {
    public byte[] sUserName = new byte[NAME_LEN];        /* 用户名 */
    public byte[] sPassword = new byte[PASSWD_LEN];        /* 密码 */
    public byte[] byLocalRight = new byte[MAX_RIGHT];    /* 本地权限 */
    public byte[] byRemoteRight = new byte[MAX_RIGHT];/* 远程权限 */
    public byte[] byNetPreviewRight = new byte[MAX_CHANNUM_V30];        /* 远程可以预览的通道 0-有权限，1-无权限*/
    public byte[] byLocalPlaybackRight = new byte[MAX_CHANNUM_V30];        /* 本地可以回放的通道 0-有权限，1-无权限*/
    public byte[] byNetPlaybackRight = new byte[MAX_CHANNUM_V30];        /* 远程可以回放的通道 0-有权限，1-无权限*/
    public byte[] byLocalRecordRight = new byte[MAX_CHANNUM_V30];        /* 本地可以录像的通道 0-有权限，1-无权限*/
    public byte[] byNetRecordRight = new byte[MAX_CHANNUM_V30];            /* 远程可以录像的通道 0-有权限，1-无权限*/
    public byte[] byLocalPTZRight = new byte[MAX_CHANNUM_V30];            /* 本地可以PTZ的通道 0-有权限，1-无权限*/
    public byte[] byNetPTZRight = new byte[MAX_CHANNUM_V30];            /* 远程可以PTZ的通道 0-有权限，1-无权限*/
    public byte[] byLocalBackupRight = new byte[MAX_CHANNUM_V30];        /* 本地备份权限通道 0-有权限，1-无权限*/
    public NET_DVR_IPADDR struUserIP = new NET_DVR_IPADDR();        /* 用户IP地址(为0时表示允许任何地址) */
    public byte[] byMACAddr = new byte[MACADDR_LEN];    /* 物理地址 */
    public byte byPriority;                /* 优先级，0xff-无，0--低，1--中，2--高 */
    public byte[] byRes = new byte[17];
}