package com.cagst.bom.person;

import java.time.LocalDate;
import java.util.Locale;

import com.cagst.bom.BaseDTO;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;

@JsonDeserialize(builder = Person.Builder.class)
@JsonPropertyOrder({
    "personId",
    "givenName",
    "commonName",
    "middleName",
    "familyName",
    "prefix",
    "suffix",
    "dateOfBirth",
    "genderCd",
    "genderIdentityCd",
    "maritalStatusCd",
    "ethnicityCd",
    "raceCd",
    "locale",
    "createdId",
    "createdDateTime",
    "active",
    "updatedId",
    "updatedDateTime",
    "updatedCount"
})
@Value.Immutable(copy = false)
@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE, overshadowImplementation = true)
public interface Person extends BaseDTO {
    @Nullable
    @Value.Auxiliary
    Long personId();

    String givenName();

    @Nullable
    String commonName();

    @Nullable
    String middleName();

    String familyName();

    @Nullable
    String prefix();

    @Nullable
    String suffix();

    @Nullable
    LocalDate dateOfBirth();

    @Nullable
    Long genderCd();

    @Nullable
    Long genderIdentityCd();

    @Nullable
    Long maritalStatusCd();

    @Nullable
    Long ethnicityCd();

    @Nullable
    Long raceCd();

    @Value.Default
    default Locale locale() {
        return Locale.US;
    }

    // static inner Builder class extends generated or yet-to-be generated Builder
    class Builder extends ImmutablePerson.Builder implements BaseDTO.Builder {}
}
