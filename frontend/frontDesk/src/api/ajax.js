import axios from 'axios';

// 携带 cookie
axios.defaults.withCredentials = true;

/*
  ajax 请求封装模块
 */
export default function ajax (method = 'GET', url, data = {}) {
  return new Promise(async (resolve, reject) => {
    // axios response 对象
    let response;
    // 拼接 url query 参数
    let dataStr = '';
    Object.keys(data).forEach(key => {
      dataStr += key + '=' + data[key] + '&';
    });
    try {
      switch (method) {
        case 'GET':
          if (dataStr !== '') {
            dataStr = dataStr.substring(0, dataStr.lastIndexOf('&'));
            url = url + '?' + dataStr;
          }
          // 发送 get
          response = await axios({
            method,
            url
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
      }
      resolve(response.data);
    } catch (e) {
      reject(e);
    }
  });
}
