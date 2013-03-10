package com.github.assisstion.spaceInvaders.gameObject;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.*;
import static java.lang.annotation.ElementType.*;

@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface Hostile{

}
