.PHONY: build
build:
	find src -name '*.java' | xargs javac -d bin -cp /ulib/antlr-4.13.2-complete.jar

.PHONY: run
run:
	cd bin && java -cp /ulib/antlr-4.13.2-complete.jar:. Main && cat ../src/Backend/builtin/builtin.s

.PHONY: testsema
testsema:
	time -p ./testcases/sema/scripts/test_all.bash "java -cp /ulib/antlr-4.13.2-complete.jar:bin Main" ./testcases/sema/

.PHONY: testIR
testIR:
	testcases/codegen/scripts/test_llvm_ir_reimu.bash 'java -cp /ulib/antlr-4.13.2-complete.jar:bin Main' testcases/codegen/e1.mx ./src/Backend/builtin/builtin.ll

.PHONY: testIRAll
testIRAll:
	testcases/codegen/scripts/test_llvm_ir_all_reimu.bash 'java -cp /ulib/antlr-4.13.2-complete.jar:bin Main' testcases/codegen/ ./src/Backend/builtin/builtin.ll

.PHONY: testASM
testASM:
	testcases/codegen/scripts/test_asm.bash 'java -cp /ulib/antlr-4.13.2-complete.jar:bin Main' testcases/codegen/t21.mx

.PHONY: testASMAll
testASMAll:
	testcases/codegen/scripts/test_asm_all.bash 'java -cp /ulib/antlr-4.13.2-complete.jar:bin Main' testcases/codegen/