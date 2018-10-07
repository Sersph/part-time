import dev from './dev.env';
import prod from './prod.env';

export default {
  API_ROOT: process.env.NODE_ENV === 'development'
    ? dev.API_ROOT
    : prod.API_ROOT
};
