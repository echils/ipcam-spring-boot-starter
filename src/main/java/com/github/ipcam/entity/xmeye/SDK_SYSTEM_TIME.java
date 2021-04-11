package com.github.ipcam.entity.xmeye;

import com.sun.jna.Structure;

/**
 * SDK_SYSTEM_TIME
 *
 * @author echils
 * @since 2018-11-29 10:56
 */
public class SDK_SYSTEM_TIME extends Structure {

    public int year;        ///< 年。

    public int month;        ///< 月，January = 1, February = 2, and so on.

    public int day;        ///< 日。

    public int wday;        ///< 星期，Sunday = 0, Monday = 1, and so on

    public int hour;        ///< 时。

    public int minute;    ///< 分。

    public int second;    ///< 秒。

    public int isdst;        ///< 夏令时标识。

}
