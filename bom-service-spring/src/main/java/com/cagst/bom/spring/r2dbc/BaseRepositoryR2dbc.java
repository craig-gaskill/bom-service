package com.cagst.bom.spring.r2dbc;

import com.cagst.bom.search.SearchCriteria;
import com.cagst.bom.util.CommonStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

/**
 * Provided common methods / functionality for an R2DBC Repository implementation.
 *
 * @author Craig Gaskill
 */
public abstract class BaseRepositoryR2dbc {
    /**
     * Will return a partial WHERE clause that supports the 'searchText' parameter of {@link SearchCriteria}.
     *
     * The 'searchText' will be converted to UPPER case for the comparison as it is intended to be compared
     * against a 'keyed' column that has been normalized and indexed for performance.
     *
     * For example, if the 'columnName' is 'name' the results are as follows.
     * <pre>
     *     searchText = "Jam"    return = "name = 'JAM'"
     *     searchText = "*Jam"   return = "name LIKE '%JAM'"
     *     searchText = "Jam*"   return = "name LIKE 'JAM%'"
     *     searchText = "*Jam*"  return = "name LIKE '%JAM%':
     * </pre>
     *
     * @param searchCriteria
     *      The {@link SearchCriteria} to use to generate a partial WHERE clause from.
     * @param columnName
     *      The name of the column to apply the WHERE clause on.
     * @param parameters
     *      A {@link SqlParameterMap} that will contain the value to associate with the binding variable.
     * @param prefixCondition
     *      A condition to prepend to the return partial WHERE clause (e.g. WHERE / AND / OR).
     *
     * @return A partial WHERE clause that supports the 'searchText' parameter.
     */
    public String appendSearchText(@Nullable SearchCriteria searchCriteria,
                                   @NonNull String columnName,
                                   @NonNull SqlParameterMap parameters,
                                   @Nullable String prefixCondition
    ) {
        Assert.hasText(columnName, "Argument [columnName] cannot be null or empty.");
        Assert.notNull(parameters, "Argument [parameters] cannot be null.");

        if (searchCriteria == null || StringUtils.isBlank(searchCriteria.searchText())) {
            return StringUtils.EMPTY;
        }

        return appendSearchText(searchCriteria.searchText(), columnName, parameters, prefixCondition);
    }

    /**
     * Will return a partial WHERE clause that supports the 'searchText'.
     *
     * The 'searchText' will be converted to UPPER case for the comparison as it is intended to be compared
     * against a 'keyed' column that has been normalized and indexed for performance.
     *
     * For example, if the 'columnName' is 'name' the results are as follows.
     * <pre>
     *     searchText = "Jam"    return = "name = 'JAM'"
     *     searchText = "*Jam"   return = "name LIKE '%JAM'"
     *     searchText = "Jam*"   return = "name LIKE 'JAM%'"
     *     searchText = "*Jam*"  return = "name LIKE '%JAM%':
     * </pre>
     *
     * @param searchText
     *      The search text to use to generate a partial WHERE clause from.
     * @param columnName
     *      The name of the column to apply the WHERE clause on.
     * @param parameters
     *      A {@link SqlParameterMap} that will contain the value to associate with the binding variable.
     * @param prefixCondition
     *      A condition to prepend to the return partial WHERE clause (e.g. WHERE / AND / OR).
     *
     * @return A partial WHERE clause that supports the 'searchText' parameter.
     */
    public String appendSearchText(@Nullable String searchText,
                                   @NonNull String columnName,
                                   @NonNull SqlParameterMap parameters,
                                   @Nullable String prefixCondition
    ) {
        Assert.hasText(columnName, "Argument [columnName] cannot be null or empty.");
        Assert.notNull(parameters, "Argument [parameters] cannot be null.");

        if (StringUtils.isBlank(searchText)) {
            return StringUtils.EMPTY;
        }

        var startsWith = StringUtils.startsWith(searchText, "*");
        var endsWith = StringUtils.endsWith(searchText, "*");

        String searchClause;
        if (startsWith || endsWith) {
            var searchString = CommonStringUtils.normalizeToKey(searchText);
            if (startsWith) {
                searchString = "%" + searchString;
            }
            if (endsWith) {
                searchString = searchString + "%";
            }

            parameters.addValue(columnName, searchString);
            searchClause = columnName + " ILIKE :" + columnName;
        } else {
            parameters.addValue(columnName, CommonStringUtils.normalizeToKey(searchText));
            searchClause = columnName + " = :" + columnName;
        }

        if (StringUtils.isNotBlank(prefixCondition)) {
            searchClause = StringUtils.appendIfMissing(StringUtils.prependIfMissing(prefixCondition, " "), " ") + searchClause;
        }

        return searchClause;
    }

    /**
     * Will return a partial WHERE clause that supports the 'start' and 'limit' parameters of {@link SearchCriteria}.
     *
     * @param searchCriteria
     *      The {@link SearchCriteria} to use to generate a partial WHERE clause from.
     * @param parameters
     *      A {@link SqlParameterMap} that will contain the value to associate with the binding variable.
     *
     * @return A partial WHERE clause that supports the 'start' and 'limit' parameters.
     */
    public String appendStartLimit(@Nullable SearchCriteria searchCriteria, @NonNull SqlParameterMap parameters) {
        String startLimitClause = StringUtils.EMPTY;

        if (searchCriteria != null) {
            if (searchCriteria.start() != null) {
                parameters.addValue("start", searchCriteria.start());
                startLimitClause += " OFFSET :start";
            }
            if (searchCriteria.limit() != null) {
                parameters.addValue("limit", searchCriteria.limit());
                startLimitClause += " LIMIT :limit";
            }
        }

        return startLimitClause;
    }
}
