import ajax from '@/api/ajax';
import config from '@/config';

export default {
  personalSignUp (params) {
    return ajax(
      'POST',
      `${config.API_ROOT}/account/personal/signUp`,
      params
    );
  },
  personalSignIn (params) {
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
  },
  sendMailCaptcha (params) {
    return ajax(
      'POST',
      `${config.API_ROOT}/account/sendMailCaptcha`,
      params
    );
  }
};
