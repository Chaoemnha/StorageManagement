package storage.dao;

import storage.model.Auth;

public interface AuthDAO<E> extends BaseDAO<E> {

	Auth find(int roleId, int menuId);
}
