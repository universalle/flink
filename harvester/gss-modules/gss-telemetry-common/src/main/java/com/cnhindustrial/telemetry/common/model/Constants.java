package com.cnhindustrial.telemetry.common.model;

import java.time.format.DateTimeFormatter;

import static com.cnhindustrial.telemetry.common.json.BaseDeserializationSchema.ZULU_TIME_FORMAT;

public class Constants {
    public static final double minLatitude = 37.018523;
    public static final double maxLatitude = 40.988993;

    public static final double minLongitude = -102.071867;
    public static final double maxLongitude = -109.047986;

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(ZULU_TIME_FORMAT);

    public static final String[] assetIds = {
            "XUZT8AVMSRTXFW5QQ",
            "II93I7PKMJQC0PJKB",
            "TIY5SZV4KVG2A6YVG",
            "4B32Y5FX2HH2WZOHJ",
            "0PQSAHRIH0TL6PJDR",
            "DL24XZQPFEV96ZUYQ",
            "C5AA4KEEIMMAD3KFO",
            "SMBIZEFCVKU9G5FQN",
            "VTJWOIYBVO4PIOBGA",
            "RR4013MCSZ0HXLAND",
            "SAXKGRKRRDEYZ3CW7",
            "IW1YYBWVCBFMCHWGJ",
            "IB3PEUHJRMAHMXLJZ",
            "DAONLZDIINZMDYYET",
            "SGMRLLORMPVEVKMRL",
            "6J6MIM75L9PNVZO79",
            "LMRCUMGTFJEASYEQK",
            "UPKAEAXCQVCFYZIBP",
            "W2DSHVNGHUCO14XYO",
            "U4OM2J51AUGYWWLBR",
            "XX6AGRG3NXKZX6SE1",
            "KKZJRPWM6FR6GIN8K",
            "KHDXK2ZJBONGNCN1W",
            "TGF5RDVCG33KLWQKE",
            "4SKPSUR7YBVU9VMA4",
            "LPJXD0NU90VDSTH5W",
            "LKVMERF9RK4PXCNIR",
            "ZAEQZPKDLFEOVQ7F6",
            "XU549XMZXUIXRZ2UV",
            "QVB1KIGGE4OS073ES"
    };

    public static final String[] familyCodes = {
            "AVG_FUEL_DISTANCE",
            "AREA",
            "AREA_REMAIN",
            "WORK_RATE_AVG",
            "ENG_SPEED",
            "ENG_LOAD",
            "DEF_RATE",
            "ENG_HOURS",
            "DRIVELINE_HOURS",
            "ENG_FUEL_RATE",
            "ENG_COOLANT_TEMP",
            "BATTERY_VOLTAGE",
            "ENG_OIL_TEMP",
            "INTAKE_TEMP",
            "DEF_LEVEL",
            "REAR_PTO_SPEED",
            "GROUND_SPEED",
            "AIR_BRAKE_PRESS",
            "ENG_BOOST_PRESS",
            "FNT_PTO_SPEED",
            "REAR_HITCH_POS",
            "TRANS_OIL_PRESS",
            "HYD_OIL_TEMP",
            "SLIP",
            "TRANS_RANGE",
            "TRANS_STATUS",
            "TRANS_LUBE_PRESS",
            "GEAR_SELECTED",
            "TRANS_OIL_TEMP",
            "FUEL_LEVEL",
            "ENG_OIL_PRESS"
    };
}
