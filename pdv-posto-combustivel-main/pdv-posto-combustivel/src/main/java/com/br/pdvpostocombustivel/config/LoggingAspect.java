package com.br.pdvpostocombustivel.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.br.pdvpostocombustivel.api..*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Executando: {} com argumentos: {}",
            joinPoint.getSignature().toShortString(),
            Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "execution(* com.br.pdvpostocombustivel.api..*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Método {} executado com sucesso", joinPoint.getSignature().toShortString());
    }

    @AfterThrowing(pointcut = "execution(* com.br.pdvpostocombustivel.api..*.*(..))", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        logger.error("Erro no método {}: {}",
            joinPoint.getSignature().toShortString(),
            error.getMessage());
    }
}

