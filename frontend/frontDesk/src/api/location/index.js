import ajax from '@/api/ajax';
import config from '@/config';

export default {
  getRegionList() {
    return ajax(
      'GET',
      `${config.API_ROOT}/common/location/regionList`,
      {}
    );
  }
};
