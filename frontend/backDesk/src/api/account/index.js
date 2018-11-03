import ajax from '@/api/ajax';
import config from '@/config';

export default {
  signIn(params) {
    return ajax(
      'POST',
      `${config.API_ROOT}/admin/account/signIn`,
      params
    );
  },
  getAccountInfo() {
    return ajax(
      'GET',
      `${config.API_ROOT}/admin/account/accountInfo`,
      {}
    );
  },
  signOut() {
    return ajax(
      'DELETE',
      `${config.API_ROOT}/admin/account/signOut`,
      {}
    );
  }
};
