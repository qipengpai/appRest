package com.company.platform.restapi.model.loan.deal;

import com.company.platform.base.validated.dictionaryValidated.DictionaryStandardOrNot;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class LoanRepaymentTrailInfo{
        /**
         * @Fields productApplyId : TODO(借贷申请id)
         */
        private String productApplyId;
        /**
         * @Fields amount : TODO(批复金额)
         */
        private String amount;
        /**
         * @Fields termCount : TODO(批准期限)
         */
        private String termCount;
        /**
         * @Fields termUnit : TODO(批准期限单位)
         */
        private String termUnit;
        /**
         * @Fields repayTermCount : TODO(还款间隔期限)
         */
        private String repayTermCount;
        /**
         * @Fields repayTermUnit : TODO(还款间隔期限单位)
         */
        private String repayTermUnit;
        /**
         * @Fields repayType : TODO(还款方式)
         */
        private String repayType;
        /**
         * @Fields interestRate : TODO(年化利率)
         */
        private String interestRate;

        public String getProductApplyId() {
            return productApplyId;
        }

        public void setProductApplyId(String productApplyId) {
            this.productApplyId = productApplyId;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getTermCount() {
            return termCount;
        }

        public void setTermCount(String termCount) {
            this.termCount = termCount;
        }

        public String getTermUnit() {
            return termUnit;
        }

        public void setTermUnit(String termUnit) {
            this.termUnit = termUnit;
        }

        public String getRepayTermCount() {
            return repayTermCount;
        }

        public void setRepayTermCount(String repayTermCount) {
            this.repayTermCount = repayTermCount;
        }

        public String getRepayTermUnit() {
            return repayTermUnit;
        }

        public void setRepayTermUnit(String repayTermUnit) {
            this.repayTermUnit = repayTermUnit;
        }

        public String getRepayType() {
            return repayType;
        }

        public void setRepayType(String repayType) {
            this.repayType = repayType;
        }

        public String getInterestRate() {
            return interestRate;
        }

        public void setInterestRate(String interestRate) {
            this.interestRate = interestRate;
        }
}
