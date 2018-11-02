import ajax from '@/api/ajax';
import config from '@/config';

export default {
  getBaseInfo() {
    return ajax(
      'GET',
      `${config.API_ROOT}/partTime/baseInfo`,
      {}
    );
  },
  partTimeAdd(params) {
    return ajax(
      'POST',
      `${config.API_ROOT}/partTime/addPartTime`,
      params
    );
  }
};
