package com.ds.xingzuo;

import android.graphics.Color;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Administrator on 2016/11/4.
 */

public class DataUtils {

    /**
     * 星座名数组
     */
    private static final String[] STAR_NAMES= {"baiyang","jinniu","shuangzi","juxie","shizi","chunv",
            "tiancheng","tianxie","sheshou","mojie","shuiping","shuangyu"};
    private static final String[] STAR_NAMES_CHINESE= {"白羊","金牛","双子","巨蟹","狮子","处女",
            "天秤","天蝎","射手","摩羯","水瓶","双鱼"};
    private static final int[] images = {R.mipmap.by,R.mipmap.jn,R.mipmap.sz,R.mipmap.jx,R.mipmap.shizi,
            R.mipmap.chunu,R.mipmap.tiancehn,R.mipmap.mojie,R.mipmap.sheshou,R.mipmap.mojiez,R.mipmap.shuiping,R.mipmap.shuangyu};

    public static Map getMap(){
        Map<String,int[]> map = new HashMap<>();
        Random random = new Random();
        for (int i = 0;i<12;i++){
            int[] ints = new int[2];
            ints[0] = images[i];
            ints[1] = Color.rgb(random.nextInt(255),random.nextInt(255),random.nextInt(255));
            map.put(STAR_NAMES[i],ints);
        }
        return map;
    }

    public static Map getMapChinese(){
        Map<String,String> map = new HashMap<>();
        for (int i = 0;i<12;i++){
            map.put(STAR_NAMES[i],STAR_NAMES_CHINESE[i]);
        }
        return map;
    }
}
