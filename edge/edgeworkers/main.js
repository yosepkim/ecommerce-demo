import { httpRequest } from 'http-request';
import { createResponse } from 'create-response';

export async function responseProvider(request) {
    try {
      const token = "eWtpbTokSWxvdmVKZXN1czEyMyE=";
      const requestBody = request.body;

      const productVariationIds = requestBody.lineItems
                                    .filter((l) =>  l.quantity > 0)
                                    .map((p) => p.productVariation.id);

      const url = `https://bb.edgecloud9.com/ecomm/inventory/check/?pvids=${productVariationIds.join(',')}`;
      const options = {
        method: 'GET',
        headers: { 'Authorization': `Basic ${token}`, 'Content-Type': 'application/json'},
      };
      const response = await httpRequest(url, options);
      const inventoryData = await response.json();

      for (let i = 0; i < inventoryData.length; i++) {
        if (inventoryData.onHandCount <= 0) {
          return createResponse(
              500,
              { 'Content-Type': ['application/json'] },
              JSON.stringify({
                  message: `PVID: ${inventoryData.id} only has ${inventoryData.onHandCount}`
              })
          );
        }
      }

      const originUrl = `${request.scheme}://${request.host}${request.url}`;
      const originHttpOptions = {
        method: "POST",
        body: requestBody
      };

      return httpRequest(originUrl, originHttpOptions).then(async response => {
        return createResponse(
          response.status,
          { 'Content-Type': ['application/json'] },
          response.body
        );
      });

    } catch (exception) {
      return createResponse(
        500,
        { 'Content-Type': ['application/json'] },
        JSON.stringify({
            path: request.path,
            error: exception,
            errorMessage: exception.message,
            stacktrace: exception.stack,
            productVariationIds: productVariationIds
        })
    )}
}

