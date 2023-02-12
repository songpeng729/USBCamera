package com.finger.usbcamera.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.finger.usbcamera.db.entity.Person;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PERSON".
*/
public class PersonDao extends AbstractDao<Person, Long> {

    public static final String TABLENAME = "PERSON";

    /**
     * Properties of entity Person.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "id");
        public final static Property PersonId = new Property(1, String.class, "personId", false, "personid");
        public final static Property Name = new Property(2, String.class, "name", false, "name");
        public final static Property IdCardNo = new Property(3, String.class, "idCardNo", false, "idcardno");
        public final static Property IdCardPhoto = new Property(4, byte[].class, "idCardPhoto", false, "idcard_photo");
        public final static Property Gender = new Property(5, String.class, "gender", false, "gender");
        public final static Property Ethnic = new Property(6, String.class, "ethnic", false, "ethnic");
        public final static Property Nationality = new Property(7, String.class, "nationality", false, "nationality");
        public final static Property Birthday = new Property(8, String.class, "birthday", false, "birthday");
        public final static Property Address = new Property(9, String.class, "address", false, "address");
        public final static Property GatherDate = new Property(10, java.util.Date.class, "gatherDate", false, "gather_date");
        public final static Property Remark = new Property(11, String.class, "remark", false, "remark");
        public final static Property GatherUserId = new Property(12, Long.class, "gatherUserId", false, "gather_user_id");
        public final static Property IdCardStatus = new Property(13, int.class, "idCardStatus", false, "idcard_status");
        public final static Property FaceStatus = new Property(14, int.class, "faceStatus", false, "face_status");
        public final static Property FingerStatus = new Property(15, int.class, "fingerStatus", false, "finger_status");
    }


    public PersonDao(DaoConfig config) {
        super(config);
    }
    
    public PersonDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PERSON\" (" + //
                "\"id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"personid\" TEXT," + // 1: personId
                "\"name\" TEXT," + // 2: name
                "\"idcardno\" TEXT," + // 3: idCardNo
                "\"idcard_photo\" BLOB," + // 4: idCardPhoto
                "\"gender\" TEXT," + // 5: gender
                "\"ethnic\" TEXT," + // 6: ethnic
                "\"nationality\" TEXT," + // 7: nationality
                "\"birthday\" TEXT," + // 8: birthday
                "\"address\" TEXT," + // 9: address
                "\"gather_date\" INTEGER," + // 10: gatherDate
                "\"remark\" TEXT," + // 11: remark
                "\"gather_user_id\" INTEGER," + // 12: gatherUserId
                "\"idcard_status\" INTEGER NOT NULL ," + // 13: idCardStatus
                "\"face_status\" INTEGER NOT NULL ," + // 14: faceStatus
                "\"finger_status\" INTEGER NOT NULL );"); // 15: fingerStatus
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PERSON\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Person entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String personId = entity.getPersonId();
        if (personId != null) {
            stmt.bindString(2, personId);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        String idCardNo = entity.getIdCardNo();
        if (idCardNo != null) {
            stmt.bindString(4, idCardNo);
        }
 
        byte[] idCardPhoto = entity.getIdCardPhoto();
        if (idCardPhoto != null) {
            stmt.bindBlob(5, idCardPhoto);
        }
 
        String gender = entity.getGender();
        if (gender != null) {
            stmt.bindString(6, gender);
        }
 
        String ethnic = entity.getEthnic();
        if (ethnic != null) {
            stmt.bindString(7, ethnic);
        }
 
        String nationality = entity.getNationality();
        if (nationality != null) {
            stmt.bindString(8, nationality);
        }
 
        String birthday = entity.getBirthday();
        if (birthday != null) {
            stmt.bindString(9, birthday);
        }
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(10, address);
        }
 
        java.util.Date gatherDate = entity.getGatherDate();
        if (gatherDate != null) {
            stmt.bindLong(11, gatherDate.getTime());
        }
 
        String remark = entity.getRemark();
        if (remark != null) {
            stmt.bindString(12, remark);
        }
 
        Long gatherUserId = entity.getGatherUserId();
        if (gatherUserId != null) {
            stmt.bindLong(13, gatherUserId);
        }
        stmt.bindLong(14, entity.getIdCardStatus());
        stmt.bindLong(15, entity.getFaceStatus());
        stmt.bindLong(16, entity.getFingerStatus());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Person entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String personId = entity.getPersonId();
        if (personId != null) {
            stmt.bindString(2, personId);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        String idCardNo = entity.getIdCardNo();
        if (idCardNo != null) {
            stmt.bindString(4, idCardNo);
        }
 
        byte[] idCardPhoto = entity.getIdCardPhoto();
        if (idCardPhoto != null) {
            stmt.bindBlob(5, idCardPhoto);
        }
 
        String gender = entity.getGender();
        if (gender != null) {
            stmt.bindString(6, gender);
        }
 
        String ethnic = entity.getEthnic();
        if (ethnic != null) {
            stmt.bindString(7, ethnic);
        }
 
        String nationality = entity.getNationality();
        if (nationality != null) {
            stmt.bindString(8, nationality);
        }
 
        String birthday = entity.getBirthday();
        if (birthday != null) {
            stmt.bindString(9, birthday);
        }
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(10, address);
        }
 
        java.util.Date gatherDate = entity.getGatherDate();
        if (gatherDate != null) {
            stmt.bindLong(11, gatherDate.getTime());
        }
 
        String remark = entity.getRemark();
        if (remark != null) {
            stmt.bindString(12, remark);
        }
 
        Long gatherUserId = entity.getGatherUserId();
        if (gatherUserId != null) {
            stmt.bindLong(13, gatherUserId);
        }
        stmt.bindLong(14, entity.getIdCardStatus());
        stmt.bindLong(15, entity.getFaceStatus());
        stmt.bindLong(16, entity.getFingerStatus());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Person readEntity(Cursor cursor, int offset) {
        Person entity = new Person( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // personId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // name
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // idCardNo
            cursor.isNull(offset + 4) ? null : cursor.getBlob(offset + 4), // idCardPhoto
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // gender
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // ethnic
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // nationality
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // birthday
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // address
            cursor.isNull(offset + 10) ? null : new java.util.Date(cursor.getLong(offset + 10)), // gatherDate
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // remark
            cursor.isNull(offset + 12) ? null : cursor.getLong(offset + 12), // gatherUserId
            cursor.getInt(offset + 13), // idCardStatus
            cursor.getInt(offset + 14), // faceStatus
            cursor.getInt(offset + 15) // fingerStatus
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Person entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPersonId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setIdCardNo(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setIdCardPhoto(cursor.isNull(offset + 4) ? null : cursor.getBlob(offset + 4));
        entity.setGender(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setEthnic(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setNationality(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setBirthday(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setAddress(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setGatherDate(cursor.isNull(offset + 10) ? null : new java.util.Date(cursor.getLong(offset + 10)));
        entity.setRemark(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setGatherUserId(cursor.isNull(offset + 12) ? null : cursor.getLong(offset + 12));
        entity.setIdCardStatus(cursor.getInt(offset + 13));
        entity.setFaceStatus(cursor.getInt(offset + 14));
        entity.setFingerStatus(cursor.getInt(offset + 15));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Person entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Person entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Person entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
