import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

class AddQuotes extends StatelessWidget {
  const AddQuotes({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    var _nameCont, quoteCont = TextEditingController();
    return SafeArea(
        child: Scaffold(
            appBar: AppBar(
              backgroundColor: Colors.teal,
              title: const Text(
                "Add Your Quotes",
                style: TextStyle(
                  fontSize: 30.0,
                  fontWeight: FontWeight.bold,
                ),
              ),
              centerTitle: true,
            ),
            body: SafeArea(
              child: Column(
                children: [
                  Container(),
                  const SizedBox(
                    height: 50,
                  ),
                  Container(
                    margin:
                        const EdgeInsets.symmetric(vertical: 20, horizontal: 0),
                    padding:
                        const EdgeInsets.symmetric(vertical: 0, horizontal: 30),
                    child: Center(
                      child: TextFormField(
                        decoration: const InputDecoration(
                            hintText: ('Enter your Name'),
                            hintStyle: TextStyle(
                              fontWeight: FontWeight.bold,
                              fontSize: 20.0,
                            )),
                        style: const TextStyle(
                            fontSize: 20.0, fontWeight: FontWeight.bold),
                      ),
                    ),
                  ),
                  Container(
                    margin:
                        const EdgeInsets.symmetric(vertical: 30, horizontal: 0),
                    padding:
                        const EdgeInsets.symmetric(vertical: 0, horizontal: 25),
                    child: const TextField(
                      keyboardType: TextInputType.multiline,
                      maxLines: null,
                      decoration: InputDecoration(
                          hintText: 'Type your quote here',
                          hintStyle: TextStyle(
                            fontWeight: FontWeight.bold,
                            fontSize: 20.0,
                          )),
                      style: TextStyle(
                          fontSize: 20.0, fontWeight: FontWeight.bold),
                    ),
                  ),
                  const SizedBox(
                    height: 50,
                  ),
                  Flex(
                    direction: Axis.vertical,
                    children: [
                      Row(
                        children: [
                          Container(
                            padding: const EdgeInsets.all(10.0),
                            margin: const EdgeInsets.symmetric(
                                vertical: 10, horizontal: 10),
                            child: TextButton(
                              style: TextButton.styleFrom(
                                backgroundColor: Colors.teal,
                              ),
                              onPressed: () {},
                              child: Row(
                                children: [
                                  Icon(
                                    Icons.save,
                                    color: Colors.teal.shade900,
                                  ),
                                  const SizedBox(
                                    width: 5,
                                  ),
                                  Text(
                                    'Save Quote',
                                    style: TextStyle(
                                      color: Colors.teal.shade900,
                                      fontFamily: "Source Sans Pro",
                                      fontSize: 20.0,
                                    ),
                                  )
                                ],
                              ),
                            ),
                          ),
                          const SizedBox(width: 5),
                          Container(
                            padding: const EdgeInsets.all(10),
                            margin: const EdgeInsets.symmetric(
                                vertical: 10, horizontal: 10),
                            child: TextButton(
                              style: TextButton.styleFrom(
                                backgroundColor: Colors.teal,
                              ),
                              onPressed: () {},
                              child: Row(
                                children: [
                                  Container(
                                    margin: const EdgeInsets.symmetric(
                                        vertical: 0, horizontal: 10),
                                    child: Row(
                                      children: [
                                        Icon(
                                          Icons.clear,
                                          color: Colors.teal.shade900,
                                        ),
                                        const SizedBox(
                                          width: 5,
                                        ),
                                        Text(
                                          'Clear     ',
                                          style: TextStyle(
                                            color: Colors.teal.shade900,
                                            fontFamily: "Source Sans Pro",
                                            fontSize: 20.0,
                                          ),
                                        )
                                      ],
                                    ),
                                  )
                                ],
                              ),
                            ),
                          ),
                        ],
                      )
                    ],
                  ),
                ],
              ),
            )));
  }
}
