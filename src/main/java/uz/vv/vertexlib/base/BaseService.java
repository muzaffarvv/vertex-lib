package uz.vv.vertexlib.base;

public interface BaseService<RQ, RS, ID> {
    RS create(RQ request);
    RS update(ID id, RQ request);
    RS getById(ID id);
    void delete(ID id);
}