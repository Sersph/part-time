const jsonServerHttp = require('json-server');
const server = jsonServerHttp.create();
const router = jsonServerHttp.router('./data.json');
const middlewares = jsonServerHttp.defaults();

// Set default middlewares (logger, static, cors and no-cache)
server.use(middlewares);

// Add this before server.use(router)
server.use(jsonServerHttp.rewriter({
  '/admin/account/signIn': '/adminAccountSignIn',
  '/admin/account/accountInfo': '/adminAccountInfo/1',
  '/admin/account/signOut': '/adminAccountSignOut'
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
        case '/admin/account/signIn':
          req.body.code = 0;
          break;
      }
    }
    if (req.method === 'DELETE') {
      switch (req.originalUrl) {
        case '/admin/account/signOut':
          res.send(JSON.stringify({
            "code": 0
          }));
          break;
      }
    }
    next();
  }, 500);
});

// Use default router
server.use(router);
server.listen(3001, () => {
  console.log('JSON Server is running');
});
