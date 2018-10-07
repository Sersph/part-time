import ajax from '@/api/ajax';
import config from '@/config';

export default {
  signUp ({ type, username, password }) {
    return ajax(
      'POST',
      `${config.API_ROOT}/account/personal/signUp`,
      {
        type,
        username,
        password
      }
    );
  },
  signIn ({ username, password }) {
    return ajax(
      'POST',
      `${config.API_ROOT}/account/personal/signIn`,
      {
        username,
        password
      }
    );
  },
  getUserInfo () {
    return ajax(
      'GET',
      `${config.API_ROOT}/account/userInfo`
    );
  }
};
