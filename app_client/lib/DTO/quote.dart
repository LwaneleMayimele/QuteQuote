class QuoteDTO {
  final String quoteID;
  final String authorName;
  final String quote;

  QuoteDTO({
    required this.authorName,
    required this.quoteID,
    required this.quote,
  });

  factory QuoteDTO.fromJson(Map<String, dynamic> json) {
    return QuoteDTO(
      quoteID: json['id'],
      authorName: json['author'],
      quote: json['quote'],
    );
  }
}
