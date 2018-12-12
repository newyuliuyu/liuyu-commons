package com.liuyu.common.mvc;

import com.google.common.collect.Maps;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * ClassName: ModelAndViewFactory <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-6-22 下午4:24 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class ModelAndViewFactory {
    private String view;
    private Map<String, Object> model = Maps.newHashMap();

    private ModelAndViewFactory(String view) {
        this.view = view;
    }

    public ModelAndViewFactory with(String name, Object value) {
        model.put(name, value);
        return this;
    }

    public ModelAndViewFactory setView(String view) {
        this.view = view;
        return this;
    }

    public ModelAndView build() {
        return new ModelAndView(view, model);
    }

    public static ModelAndViewFactory instance() {
        return new ModelAndViewFactory("");
    }

    public static ModelAndViewFactory instance(String view) {
        return new ModelAndViewFactory(view);
    }
}
