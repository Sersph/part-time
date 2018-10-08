const jsonServerHttp = require('json-server');
const server = jsonServerHttp.create();
const router = jsonServerHttp.router('./data.json');
const middlewares = jsonServerHttp.defaults();

// Set default middlewares (logger, static, cors and no-cache)
server.use(middlewares);

// Add this before server.use(router)
server.use(jsonServerHttp.rewriter({
  '/account/personal/signUp': '/accountPersonalSignUp',
  '/account/personal/signIn': '/accountPersonalSignIn',
  '/account/signOut': '/accountSignOut/1',
  '/account/userInfo': '/accountUserInfo/1',
  '/common/location/regionList': '/commonLocationRegionList/1'
}));

// Add custom routes before JSON Server router
server.get('/echo', (req, res) => {
  res.jsonp(req.query);
});

// To handle POST, PUT and PATCH you need to use a body-parser
// You can use the one used by JSON Server
server.use(jsonServerHttp.bodyParser);
server.use((req, res, next) => {
  if (req.method === 'POST') {
    switch (req.originalUrl) {
      case '/account/personal/signUp':
      case '/account/personal/signIn':
        req.body.code = 0;
        break;
    }
  }
  // Continue to JSON Server router
  next();
});

// Use default router
server.use(router);
server.listen(3000, () => {
  console.log('JSON Server is running');
});
