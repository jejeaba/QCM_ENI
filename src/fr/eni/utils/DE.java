/**
 *  Classe en charge de.
 */
package fr.eni.utils;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD ) //on class level
public @interface DE {
	
	boolean isPrimary() default false;
	String field() default "";

}
