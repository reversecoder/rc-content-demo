package com.reversecoder.content.nonmedia.contact;

import android.database.Cursor;
import android.provider.ContactsContract;

import com.reversecoder.content.helper.model.Address;
import com.reversecoder.content.helper.model.Email;
import com.reversecoder.content.helper.model.Event;
import com.reversecoder.content.helper.model.PhoneNumber;

class CursorHelper {
    private final Cursor c;

    CursorHelper(Cursor c) {
        this.c = c;
    }

    Long getContactId() {
        return getLong(c, ContactsContract.RawContacts.CONTACT_ID);
    }

    String getMimeType() {
        return getString(c, ContactsContract.Data.MIMETYPE);
    }

    String getDisplayName() {
        return getString(c, ContactsContract.Data.DISPLAY_NAME);
    }

    String getGivenName() {
        return getString(c, ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME);
    }

    String getFamilyName() {
        return getString(c, ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME);
    }

    String getCompanyName() {
        return getString(c, ContactsContract.CommonDataKinds.Organization.COMPANY);
    }

    String getCompanyTitle() {
        return getString(c, ContactsContract.CommonDataKinds.Organization.TITLE);
    }

    String getWebsite() {
        return getString(c, ContactsContract.CommonDataKinds.Website.URL);
    }

    String getNote() {
        return getString(c, ContactsContract.CommonDataKinds.Note.NOTE);
    }

    Address getAddress() {
        String address = getString(c, ContactsContract.CommonDataKinds.StructuredPostal.FORMATTED_ADDRESS);
        if (address == null) {
            return null;
        }

        Integer typeValue = getInt(c, ContactsContract.CommonDataKinds.StructuredPostal.TYPE);
        Address.Type type = typeValue == null ? Address.Type.UNKNOWN : Address.Type.fromValue(typeValue);

        String street = getString(c, ContactsContract.CommonDataKinds.StructuredPostal.STREET);
        String city = getString(c, ContactsContract.CommonDataKinds.StructuredPostal.CITY);
        String region = getString(c, ContactsContract.CommonDataKinds.StructuredPostal.REGION);
        String postcode = getString(c, ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE);
        String country = getString(c, ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY);

        if (!type.equals(Address.Type.CUSTOM)) {
            return new Address(address, street, city, region, postcode, country, type);
        }

        String label = getString(c, ContactsContract.CommonDataKinds.StructuredPostal.LABEL);
        return new Address(address, street, city, region, postcode, country, label);
    }

    PhoneNumber getPhoneNumber() {
        String number = getString(c, ContactsContract.CommonDataKinds.Phone.NUMBER);
        if (number == null) {
            return null;
        }

        String normalizedNumber = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            normalizedNumber = getString(c, ContactsContract.CommonDataKinds.Phone.NORMALIZED_NUMBER);
        }

        Integer typeValue = getInt(c, ContactsContract.CommonDataKinds.Phone.TYPE);
        PhoneNumber.Type type = typeValue == null ? PhoneNumber.Type.UNKNOWN :
                PhoneNumber.Type.fromValue(typeValue);
        if (!type.equals(PhoneNumber.Type.CUSTOM)) {
            return new PhoneNumber(number, type, normalizedNumber);
        }

        return new PhoneNumber(number,
                getString(c, ContactsContract.CommonDataKinds.Phone.LABEL), normalizedNumber);
    }

    Email getEmail() {
        String address = getString(c, ContactsContract.CommonDataKinds.Email.ADDRESS);
        if (address == null) {
            return null;
        }

        Integer typeValue = getInt(c, ContactsContract.CommonDataKinds.Email.TYPE);
        Email.Type type = typeValue == null ? Email.Type.UNKNOWN : Email.Type.fromValue(typeValue);
        if (!type.equals(Email.Type.CUSTOM)) {
            return new Email(address, type);
        }

        return new Email(address, getString(c, ContactsContract.CommonDataKinds.Email.LABEL));
    }

    String getPhotoUri() {
        return getString(c, ContactsContract.Data.PHOTO_URI);
    }


    Event getEvent() {
        String startDate = getString(c, ContactsContract.CommonDataKinds.Event.START_DATE);
        if (startDate == null) {
            return null;
        }

        Integer typeValue = getInt(c, ContactsContract.CommonDataKinds.Event.TYPE);
        Event.Type type = typeValue ==  null ? Event.Type.UNKNOWN : Event.Type.fromValue(typeValue);
        if (!type.equals(Event.Type.CUSTOM)) {
            return new Event(startDate, type);
        }

        return new Event(startDate, getString(c, ContactsContract.CommonDataKinds.Event.LABEL));
    }

    private String getString(Cursor c, String column) {
        int index = c.getColumnIndex(column);
        return index == -1 ? null : c.getString(index);
    }

    private Integer getInt(Cursor c, String column) {
        int index = c.getColumnIndex(column);
        return index == -1 ? null : c.getInt(index);
    }

    private  Long getLong(Cursor c, String column) {
        int index = c.getColumnIndex(column);
        return index == -1 ? null : c.getLong(index);
    }
}
