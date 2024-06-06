'use strict';

/**
 * landing-page service
 */

const { createCoreService } = require('@strapi/strapi').factories;

module.exports = createCoreService('api::landing-page.landing-page');
