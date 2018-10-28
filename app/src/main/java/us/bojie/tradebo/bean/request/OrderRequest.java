package us.bojie.tradebo.bean.request;

import com.google.gson.annotations.SerializedName;

import us.bojie.tradebo.utils.Constants;

public class OrderRequest {
    private final String account;
    private final String instrument;
    private final String symbol;
    private final String type;
    @SerializedName("time_in_force")
    private final String timeInForce;
    private final String trigger;
    private final String quantity;
    private final String side;
    @SerializedName("stop_price")
    private final String stopPrice;

    private OrderRequest(Builder builder) {
        this.account = builder.account;
        this.instrument = builder.instrument;
        this.symbol = builder.symbol;
        this.type = builder.type;
        this.timeInForce = builder.timeInForce;
        this.trigger = builder.trigger;
        this.quantity = builder.quantity;
        this.side = builder.side;
        this.stopPrice = builder.stopPrice;
    }

    public static Builder builder(String instrument, String symbol, String side,
                                  String quantity, String stopPrice) {
        return new Builder(instrument, symbol, side, quantity, stopPrice);
    }

    public static class Builder {
        private String account;
        private String instrument;
        private String symbol;
        private String type;
        private String timeInForce;
        private String trigger;
        private String quantity;
        private String side;
        private String stopPrice;

        Builder(String instrument, String symbol, String side, String quantity, String stopPrice) {
            this.instrument = instrument;
            this.symbol = symbol;
            // FIXME temp
            this.account = Constants.ACCOUNT;
            this.side = side;
            this.quantity = quantity;
            this.stopPrice = stopPrice;
        }

        public Builder type(String type) {
            this.type = type != null ? type : Constants.KEY_MARKET;
            return this;
        }

        public Builder timeInForce(String timeInForce) {
            this.timeInForce = timeInForce != null ? timeInForce : "gtc";
            return this;
        }

        public Builder trigger(String trigger) {
            this.trigger = trigger != null ? trigger : "stop";
            return this;
        }

        public OrderRequest build() {
            return new OrderRequest(this);
        }
    }
}
