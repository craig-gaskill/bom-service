package com.cagst.bom.dictionary;

import java.util.function.Function;

import io.r2dbc.spi.Row;

/**
 * Will map a {@link Row} into a {@link Dictionary}.
 *
 * @author Craig Gaskill
 */
/* package */ class DictionaryMapper implements Function<Row, Dictionary> {
    @Override
    public Dictionary apply(Row row) {
        Dictionary.Builder builder = new Dictionary.Builder()
            .dictionaryId(row.get("dictionary_id", Long.class))
            .display(row.get("display", String.class))
            .meaning(row.get("meaning", String.class))
            .description(row.get("description", String.class))
            .viewable(row.get("viewable_ind", Boolean.class))
            .editable(row.get("editable_ind", Boolean.class))
            .deletable(row.get("deletable_ind", Boolean.class))
            .active(row.get("active_ind", Boolean.class))
            .updatedCount(row.get("updated_cnt", Integer.class));

        return builder.build();
    }
}
