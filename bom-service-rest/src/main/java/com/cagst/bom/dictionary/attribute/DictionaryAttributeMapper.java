package com.cagst.bom.dictionary.attribute;

import java.util.function.Function;

import io.r2dbc.spi.Row;

/**
 * Will map a {@link Row} into a {@link DictionaryAttribute}.
 *
 * @author Craig Gaskill
 */
/* package */ class DictionaryAttributeMapper implements Function<Row, DictionaryAttribute> {
    @Override
    public DictionaryAttribute apply(Row row) {
        DictionaryAttribute.Builder builder = new DictionaryAttribute.Builder()
            .dictionaryAttributeId(row.get("dictionary_attribute_id", Long.class))
            .display(row.get("display", String.class))
            .meaning(row.get("meaning", String.class))
            .description(row.get("description", String.class))
            .attributeType(DictionaryAttributeType.values()[row.get("attribute_type", Short.class)])
            .viewable(row.get("viewable_ind", Boolean.class))
            .editable(row.get("editable_ind", Boolean.class))
            .deletable(row.get("deletable_ind", Boolean.class))
            .sortOrder(row.get("sort_order", Integer.class))
            .active(row.get("active_ind", Boolean.class))
            .updatedCount(row.get("updated_cnt", Integer.class));

        return builder.build();
    }
}
