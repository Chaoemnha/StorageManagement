package storage.dao;

import storage.dto.RoleDTO;

public interface RoleDAO<E> extends BaseDAO<E> {
	public RoleDTO findRoleWithRoleId(Integer id);
}
