import ajax from '@/api/ajax';
import config from '@/config';

export default {
  signUp (params) {
    return ajax(
      'POST',
      `${config.API_ROOT}/account/personal/signUp`,
      params
    );
  },
  signIn (params) {
    return ajax(
      'POST',
      `${config.API_ROOT}/account/personal/signIn`,
      params
    );
  },
  signOut () {
    return ajax(
      'GET',
      `${config.API_ROOT}/account/signOut`,
      {}
    );
  },
  getUserInfo () {
    return ajax(
      'GET',
      `${config.API_ROOT}/account/userInfo`,
      {}
    );
  }
};
