const inventoryCheck = async (server, { hdbCore, logger }) => {
   server.get("/inventory/check", async (request, reply) => {
    try {
      const productVariationIds = request.query.pvids.split(',');
      const conditions = productVariationIds.map((p) => {
        return {
          search_attribute: 'productionVariationId',
          search_type: "equals",
          search_value: parseInt(p)
        }
      });

      const selectQuery = {
        body: {
          operation: 'search_by_conditions',
          database: 'ecommerce',
          table: 'inventory',
          conditions: conditions,
          get_attributes: ['id', 'productionVariationId', 'onHandCount']
        }
      };

      if (conditions.length > 1) selectQuery['body']['operator'] = 'or';
      const records = await hdbCore.requestWithoutAuthentication(selectQuery);
      reply
        .code(200)
        .header('Content-Type', 'application/json; charset=utf-8')
        .type('application/json')
        .send(JSON.stringify(records));

    } catch (exception) {
      reply
        .code(500)
        .header('Content-Type', 'application/json; charset=utf-8')
        .type('application/json')
        .send(JSON.stringify({ error: exception }));
    }
    });
 }
 export default inventoryCheck;