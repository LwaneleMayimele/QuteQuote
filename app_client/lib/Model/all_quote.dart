class ServiceResult {
  final String quoteId;
  final String name;
  final String quote;

  ServiceResult({
    required this.name,
    required this.quoteId,
    required this.quote});

  factory ServiceResult.fromJson(Map<String, dynamic> json) {
    return ServiceResult(
      quoteId: json['userId'],
      name: json['id'],
      quote: json['title'],
    );
  }
}
