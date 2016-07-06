/**
 *  Classe en charge de.
 */
package fr.eni.bo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author blemaitre2015
 * @date 6 juil. 2016
 * @version QCM_ENI - V1.0
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface HeritsFrom {

	String classe() default "";
}
