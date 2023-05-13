package com.iscas.biz.calculation.util;

import com.sun.jna.Library;
import com.sun.jna.Native;

import java.io.File;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/24 11:20
 */
public interface ShipConfigLib extends Library {
    ShipConfigLib INSTANCE = Native.load("dll" + File.separator + "shipconfig_r.dll", ShipConfigLib.class);
}
