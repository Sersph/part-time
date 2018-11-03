import axios from 'axios';
import commonUtils from '@/utils/common';

// 携带 cookie
axios.defaults.withCredentials = true;
axios.defaults.retry = 4;
axios.defaults.retryDelay = 1;

// axios 拦截器
axios.interceptors.response.use(
  response => {
    return response;
  },
  error => {
    if (error.response) {
      var config = error.config;
      // If config does not exist or the retry option is not set, reject
      if (!config || !config.retry) return Promise.reject(error);

      // Set the variable for keeping track of the retry count
      config.__retryCount = config.__retryCount || 0;

      // Check if we've maxed out the total number of retries
      if (config.__retryCount >= config.retry) {
        // Reject with the erroror
        return Promise.reject(error);
      }

      // Increase the retry count
      config.__retryCount += 1;

      if (error.response.status === 401) {
        // 解析请求参数
        const queryObj = commonUtils.decodeUrl(config.url);
        // 删除请求参数 token
        delete queryObj.access_token;
        config.url = config.url.substr(0, config.url.indexOf('?')) + '?' + commonUtils.encodeUrl(queryObj);

        // 删除本地 token
        window.localStorage.removeItem('access_token');
        var backoff = new Promise(function (resolve) {
          setTimeout(function () {
            resolve();
          }, config.retryDelay || 1);
        });

        return backoff.then(function () {
          // 重发
          return axios(config);
        });
      }
    }
  });

/*
  ajax 请求封装模块
 */
export default function ajax(method = 'GET', url, data = {}) {
  return new Promise(async (resolve, reject) => {
    // axios response 对象
    let response;
    // 拼接 access_token
    if (window.localStorage.getItem('access_token') !== null) {
      url += `?access_token=${window.localStorage.getItem('access_token')}`;
    }
    // 拼接 url query 参数
    let dataStr = '';
    if (JSON.stringify(data) !== '{}') {
      dataStr = commonUtils.encodeUrl(data);
    }
    try {
      switch (method) {
        case 'GET':
          // 发送 get
          response = await axios({
            method,
            url,
            params: data
          });
          break;
        case 'POST':
          // 发送 post
          response = await axios({
            method,
            url,
            data,
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded'
            },
            transformRequest: [function () {
              return dataStr;
            }]
          });
          break;
        case 'DELETE':
          // 发送 delete
          response = await axios({
            method,
            url,
            data
          });
      }
      resolve(response.data);
    } catch (e) {
      reject(e);
    }
  });
}
