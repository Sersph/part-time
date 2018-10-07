import axios from 'axios';

// 携带 cookie
axios.defaults.withCredentials = true;

/*
  ajax 请求封装模块
 */
export default function ajax (url, data = {}, type = 'GET') {
  return new Promise(async (resolve, reject) => {
    let response;
    try {
      switch (type) {
        case 'GET':
          // 拼接 url query 参数
          let dataStr = '';
          Object.keys(data).forEach(key => {
            dataStr += key + '=' + data[key] + '&';
          });
          if (dataStr !== '') {
            dataStr = dataStr.substring(0, dataStr.lastIndexOf('&'));
            url = url + '?' + dataStr;
          }
          // 发送 get
          response = await axios.get(url);
          break;
        case 'POST':
          // 发送 post
          response = await axios.post(url, data);
      }
      resolve(response.data);
    } catch (e) {
      reject(e);
    }
  });
}
