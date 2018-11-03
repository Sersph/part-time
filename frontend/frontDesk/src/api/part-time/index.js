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
  addPartTime(params) {
    return ajax(
      'POST',
      `${config.API_ROOT}/partTime/addPartTime`,
      params
    );
  },
  getPartTime(params) {
    return ajax(
      'GET',
      `${config.API_ROOT}/partTime/getPartTime`,
      params
    );
  },
  getPartTimeDetail(id) {
    return ajax(
      'GET',
      `${config.API_ROOT}/partTime/getPartTime/${id}`,
      {}
    );
  }
};
