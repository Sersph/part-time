const jsonServerHttp = require('json-server');
const server = jsonServerHttp.create();
const router = jsonServerHttp.router('./data.json');
const middlewares = jsonServerHttp.defaults();
const URL = require('url');

// Set default middlewares (logger, static, cors and no-cache)
server.use(middlewares);

// Add this before server.use(router)
server.use(jsonServerHttp.rewriter({
  '/common/location/regionList': '/commonLocationRegionList/1',
  '/account/personal/signUp': '/accountPersonalSignUp',
  '/account/personal/signIn': '/accountPersonalSignIn',
  '/account/enterprise/signUp': '/accountEnterpriseSignUp',
  '/account/enterprise/signIn': '/accountEnterpriseSignIn',
  '/account/signOut': '/accountSignOut/1',
  '/account/accountInfo': '/accountAccountInfo/1',
  '/account/sendMailCaptcha': '/sendMailCaptcha',
  '/partTime/baseInfo': '/partTimeBaseInfo/1',
  '/partTime/getPartTime': '/partTimePartTime'
}));

// Add custom routes before JSON Server router
server.get('/echo', (req, res) => {
  res.jsonp(req.query);
});

// To handle POST, PUT and PATCH you need to use a body-parser
// You can use the one used by JSON Server
server.use(jsonServerHttp.bodyParser);
server.use((req, res, next) => {
  var url = URL.parse(req.url);
  setTimeout(function () {
    if (req.method === 'GET') {
      switch (url.pathname) {
        case '/partTime/getPartTime':
          res.end(JSON.stringify({
              "total": 59,
              "rows": [
                {
                  "id": 7,
                  "name": "简单小任务，在家躺赚",
                  "price": 300.0,
                  "recruitmentCount": 30,
                  "area": "荔湾区",
                  "calculationType": "天",
                  "partTimeSpecies": "打包分拣",
                  "partTimeType": "短期兼职",
                  "settlementType": "月结"
                },
                {
                  "id": 72,
                  "name": "简单小任务，在家躺赚",
                  "price": 300.0,
                  "recruitmentCount": 30,
                  "area": "荔湾区",
                  "calculationType": "天",
                  "partTimeSpecies": "打包分拣",
                  "partTimeType": "短期兼职",
                  "settlementType": "月结"
                },
                {
                  "id": 73,
                  "name": "简单小任务，在家躺赚",
                  "price": 300.0,
                  "recruitmentCount": 30,
                  "area": "荔湾区",
                  "calculationType": "天",
                  "partTimeSpecies": "打包分拣",
                  "partTimeType": "短期兼职",
                  "settlementType": "月结"
                },
                {
                  "id": 74,
                  "name": "简单小任务，在家躺赚",
                  "price": 300.0,
                  "recruitmentCount": 30,
                  "area": "荔湾区",
                  "calculationType": "天",
                  "partTimeSpecies": "打包分拣",
                  "partTimeType": "短期兼职",
                  "settlementType": "月结"
                },
                {
                  "id": 7,
                  "name": "简单小任务，在家躺赚",
                  "price": 300.0,
                  "recruitmentCount": 30,
                  "area": "荔湾区",
                  "calculationType": "天",
                  "partTimeSpecies": "打包分拣",
                  "partTimeType": "短期兼职",
                  "settlementType": "月结"
                },

                {
                  "id": 112,
                  "name": "简单小任务，在家躺赚",
                  "price": 1.0,
                  "recruitmentCount": 0,
                  "area": "荔湾区",
                  "calculationType": "天",
                  "partTimeSpecies": "打包分拣",
                  "partTimeType": "短期兼职",
                  "settlementType": "月结"
                },
                {
                  "id": 113,
                  "name": "简单小任务，在家躺赚",
                  "price": 10.0,
                  "recruitmentCount": 10,
                  "area": "荔湾区",
                  "calculationType": "个",
                  "partTimeSpecies": "打包分拣",
                  "partTimeType": "短期兼职",
                  "settlementType": "月结"
                },
                {
                  "id": 114,
                  "name": "简单小任务，在家躺赚",
                  "price": 11.0,
                  "recruitmentCount": 20,
                  "area": "荔湾区",
                  "calculationType": "个",
                  "partTimeSpecies": "打包分拣",
                  "partTimeType": "短期兼职",
                  "settlementType": "次日结"
                },
                {
                  "id": 115,
                  "name": "简单小任务，在家躺赚",
                  "price": 11.0,
                  "recruitmentCount": 20,
                  "area": "荔湾区",
                  "calculationType": "个",
                  "partTimeSpecies": "打包分拣",
                  "partTimeType": "短期兼职",
                  "settlementType": "次日结"
                },
                {
                  "id": 116,
                  "name": "简单小任务，在家躺赚",
                  "price": 11.0,
                  "recruitmentCount": 11,
                  "area": "荔湾区",
                  "calculationType": "个",
                  "partTimeSpecies": "打包分拣",
                  "partTimeType": "短期兼职",
                  "settlementType": "月结"
                },
                {
                  "id": 125,
                  "name": "简单小任务，在家躺赚",
                  "price": 123.0,
                  "recruitmentCount": 123,
                  "area": "荔湾区",
                  "calculationType": "个",
                  "partTimeSpecies": "打包分拣",
                  "partTimeType": "短期兼职",
                  "settlementType": "月结"
                },
                {
                  "id": 126,
                  "name": "简222单小任务，在家躺赚",
                  "price": 222.0,
                  "recruitmentCount": 10,
                  "area": "莞城区",
                  "calculationType": "周",
                  "partTimeSpecies": "打包分拣",
                  "partTimeType": "短期兼职",
                  "settlementType": "次日结"
                },
                {
                  "id": 127,
                  "name": "简单小任务，在家躺赚",
                  "price": 10.0,
                  "recruitmentCount": 10,
                  "area": "莞城区",
                  "calculationType": "个",
                  "partTimeSpecies": "打包分拣",
                  "partTimeType": "短期兼职",
                  "settlementType": "月结"
                }
              ],
              "pageNumber": 1,
              "pageSize": 10,
              "pages": 5
            }));
          // res.end(JSON.stringify({
          //   "total": 9,
          //   "rows": [
          //   ],
          //   "pageNumber": 1,
          //   "pageSize": 10,
          //   "pages": 1
          // }));
          return;
      }
    }
    if (req.method === 'POST') {
      switch (url.pathname) {
        case '/account/personal/signUp':
        case '/account/personal/signIn':
        case '/account/enterprise/signUp':
        case '/account/enterprise/signIn':
        case '/account/sendMailCaptcha':
          break;
        case '/partTime/partTime':
          req.body.code = 0;
          break;
      }
    }
    if (req.method === 'DELETE') {
      switch (url.pathname) {
        case '/account/signOut':
          res.send(JSON.stringify({
            'code': 0
          }));
          break;
      }
    }
    next();
  }, 200);
});

// Use default router
server.use(router);
server.listen(3001, () => {
  console.log('JSON Server is running');
});
