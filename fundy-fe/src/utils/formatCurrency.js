const formatCurrency = (amount, locale = 'ko-KR', currency = 'KRW') => {
    return new Intl.NumberFormat(locale, {
        style: 'currency',
        currency: currency,
        currencyDisplay: 'symbol'
    }).format(amount).replace(/[₩]/g, '').trim();
}

export default formatCurrency;