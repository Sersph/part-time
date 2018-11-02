const jsonServerHttp = require('json-server');
const server = jsonServerHttp.create();
const router = jsonServerHttp.router('./data.json');
const middlewares = jsonServerHttp.defaults();

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
  '/partTime/partTime': '/partTimePartTime'
}));

// Add custom routes before JSON Server router
server.get('/echo', (req, res) => {
  res.jsonp(req.query);
});

// To handle POST, PUT and PATCH you need to use a body-parser
// You can use the one used by JSON Server
server.use(jsonServerHttp.bodyParser);
server.use((req, res, next) => {
  setTimeout(function () {
    if (req.method === 'POST') {
      switch (req.originalUrl) {
        case '/account/personal/signUp':
        case '/account/personal/signIn':
        case '/account/enterprise/signUp':
        case '/account/enterprise/signIn':
        case '/account/sendMailCaptcha':
        case '/partTime/partTime':
          req.body.code = 0;
          break;
      }
    }
    if (req.method === 'DELETE') {
      switch (req.originalUrl) {
        case '/account/signOut':
          res.send(JSON.stringify({
            'code': 0
          }));
          break;
      }
    }
    next();
  }, 500);
});

// Use default router
server.use(router);
server.listen(3000, () => {
  console.log('JSON Server is running');
});
