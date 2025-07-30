package storage.dao;

import java.util.List;

import storage.dto.UserRoleDTO;

public interface UserRoleDAO<E> extends BaseDAO<E> {
	public List<UserRoleDTO> findUserRoleDTOsByUserId(Integer id);
}
