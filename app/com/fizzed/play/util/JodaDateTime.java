package com.fizzed.play.util;

import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@play.data.Form.Display(name = "format.joda.datetime", attributes = { "format", "zone" })
public @interface JodaDateTime {
    String format() default "";
    String zone() default "";
}