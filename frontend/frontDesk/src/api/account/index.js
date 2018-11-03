import ajax from '@/api/ajax';
import config from '@/config';

export default {
  personalSignUp(params) {
    return ajax(
      'POST',
      `${config.API_ROOT}/account/personal/signUp`,
      params
    );
  },
  personalSignIn(params) {
    return ajax(
      'POST',
      `${config.API_ROOT}/account/personal/signIn`,
      params
    );
  },
  enterpriseSignUp(params) {
    return ajax(
      'POST',
      `${config.API_ROOT}/account/enterprise/signUp`,
      params
    );
  },
  enterpriseSignIn(params) {
    return ajax(
      'POST',
      `${config.API_ROOT}/account/personal/signIn`,
      params
    );
  },
  signOut() {
    return ajax(
      'DELETE',
      `${config.API_ROOT}/account/signOut`,
      {}
    );
  },
  getAccountInfo() {
    return ajax(
      'GET',
      `${config.API_ROOT}/account/accountInfo`,
      {}
    );
  },
  sendMailCaptcha(params) {
    return ajax(
      'POST',
      `${config.API_ROOT}/account/sendMailCaptcha`,
      params
    );
  }
};
