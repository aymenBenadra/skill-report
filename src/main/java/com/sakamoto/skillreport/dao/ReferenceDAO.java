package com.sakamoto.skillreport.dao;

import com.sakamoto.skillreport.dao.interfaces.IReferenceDAO;
import com.sakamoto.skillreport.model.Reference;
import org.springframework.stereotype.Repository;

@Repository
public class ReferenceDAO extends BaseDAO<Reference> implements IReferenceDAO {

    public ReferenceDAO() {
        super(Reference.class);
    }

    @Override
    public Reference read(String code) {
        Reference reference = new Reference();
        reference.setCode(code);
        return template.findByExample(reference).get(0);
    }
}
