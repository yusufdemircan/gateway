package com.yedy.gateway.interceptor;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class GatewayRequestFilter extends AbstractGatewayFilterFactory<GatewayRequestFilter.Config> {

    public GatewayRequestFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // İstek üzerinde özelleştirilmiş işlemler burada yapılabilir.

            // Örneğin, isteği kontrol edebilir ve bazı koşullara bağlı olarak işlemler yapabilirsiniz.
            if (config.isEnabled()) {
                // Eğer koşul sağlanıyorsa işlemler burada yapılır.
                // Örnek olarak, isteği engelleyebilir veya değiştirebilirsiniz.
                return chain.filter(exchange);
            } else {
                // Eğer koşul sağlanmıyorsa, hata durumunda 403 Forbidden yanıtı verilir.
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                return exchange.getResponse().setComplete();
            }
        };
    }

    public static class Config {
        private boolean enabled;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }
}
