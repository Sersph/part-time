import Format from 'date-fns/format';
import Vue from 'vue';

Vue.filter('dateFormat', function (value, format = 'YYYY-MM-DD HH:mm:ss') {
  return Format(value).format(format);
});
