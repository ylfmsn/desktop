package com.suntoon.map.plugins;

import com.suntoon.map.MapListener;
import com.suntoon.map.event.MapPaintListener;

/**
 * @ProjectionName desktop
 * @ClassName Plugin
 * @Description 插件接口对象
 * @Author YueLifeng
 * @Date 2019/4/29 0029上午 11:02
 * @Version 1.0
 */
public interface Plugin extends MapPaintListener, MapListener {

    //插件为图层插件
    public static final String GROUP_LAYER = "layergroup";

    //工具组插件
    public static final String GROUP_TOOLS = "toolsgroup";

    //没有分组，可能不需要显示在ui上的插件
    public static final String GROUP_EMPTY = "emptygroup";

    //插件名称
    public String getTitle();

    //获取插件所在分组
    public String getGroup();
}
