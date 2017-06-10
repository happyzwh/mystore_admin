package com.mystore.business.annotation;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class ExcludeAnnotationExclusionStrategy implements ExclusionStrategy {
	   public boolean shouldSkipClass(Class<?> clazz) {
	     return clazz.getAnnotation(ExcludeAnnotation.class) != null;
	   }

	   public boolean shouldSkipField(FieldAttributes f) {
	     return f.getAnnotation(ExcludeAnnotation.class) != null;
	   }
}
