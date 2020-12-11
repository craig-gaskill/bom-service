package com.cagst.bom.dictionary.value;

import java.util.function.Function;

import io.r2dbc.spi.Row;

/**
 * Will map a {@link Row} into a {@link DictionaryValue}.
 *
 * @author Craig Gaskill
 */
/* package */ class DictionaryValueMapper implements Function<Row, DictionaryValue> {
    @Override
    public DictionaryValue apply(Row row) {
        DictionaryValue.Builder builder = new DictionaryValue.Builder()
            .dictionaryValueId(row.get("dictionary_value_id", Long.class))
            .display(row.get("display", String.class))
            .meaning(row.get("meaning", String.class))
            .description(row.get("description", String.class))
            .viewable(row.get("viewable_ind", Boolean.class))
            .editable(row.get("editable_ind", Boolean.class))
            .deletable(row.get("deletable_ind", Boolean.class))
            .sortOrder(row.get("sort_order", Integer.class))
            .active(row.get("active_ind", Boolean.class))
            .updatedCount(row.get("updated_cnt", Integer.class));

        return builder.build();
    }
}
