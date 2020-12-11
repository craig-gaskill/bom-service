package com.cagst.bom.spring.r2dbc;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.util.Assert;

/**
 * Allows for parameters to be defined in a map to be bound to a DatabaseClient once all the parameters
 * and the final query has been determined and generated.
 *
 * @author Craig Gaskill
 */
public class SqlParameterMap {
    private Map<String, Pair<Object, Class<?>>> parameters = new ConcurrentHashMap<>();

    /**
     * Add a parameter to this parameter map.
     *
     * @param parameterName
     *      The name of the parameter to add.
     * @param value
     *      The value to associate with the parameter.
     *
     * @return A reference to this parameter map so it can be chained.
     */
    public SqlParameterMap addValue(@NonNull String parameterName, @NonNull Object value) {
        Assert.hasText(parameterName, "Argument [parameterName] cannot be null or empty.");
        Assert.notNull(value, "Argument [value] cannot be null.");

        parameters.put(parameterName, Pair.of(value, value.getClass()));

        return this;
    }

    /**
     * Add a parameter to this parameter map.
     *
     * @param parameterName
     *      The name of the parameter to add.
     * @param value
     *      The value to associate with the parameter.
     * @param clazz
     *      The Class (type) of the parameter (in case of a null value).
     *
     * @return A reference to this parameter map so it can be chained.
     */
    public SqlParameterMap addValue(@NonNull String parameterName, @Nullable Object value, @NonNull Class<?> clazz) {
        Assert.hasText(parameterName, "Argument [parameterName] cannot be null or empty.");
        Assert.notNull(clazz, "Argument [clazz] cannot be null.");

        if (value == null) {
            parameters.put(parameterName, Pair.of(Optional.empty(), clazz));
        } else {
            parameters.put(parameterName, Pair.of(value, clazz));
        }

        return this;
    }

    /**
     * Will bind the parameters specified to the execution spec.
     *
     * @param executeSpec
     *      The {@link DatabaseClient.GenericExecuteSpec} to bind parameters to.
     *
     * @return The {@link DatabaseClient.GenericExecuteSpec} after it has had the parameters bound to it.
     */
    public DatabaseClient.GenericExecuteSpec bind(@NonNull DatabaseClient.GenericExecuteSpec executeSpec) {
        Assert.notNull(executeSpec, "Argument [executeSpec] cannot be null.");

        for (Map.Entry<String, Pair<Object, Class<?>>> entry : parameters.entrySet()) {
            if (entry.getValue().getLeft() instanceof Optional) {
                if (((Optional) entry.getValue().getLeft()).isEmpty()) {
                    executeSpec = executeSpec.bindNull(entry.getKey(), entry.getValue().getRight());
                } else {
                    executeSpec = executeSpec.bind(entry.getKey(), ((Optional) entry.getValue().getLeft()).get());
                }
            } else {
                executeSpec = executeSpec.bind(entry.getKey(), entry.getValue().getLeft());
            }
        }

        return executeSpec;
    }
}
