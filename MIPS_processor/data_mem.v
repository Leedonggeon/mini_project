module data_memory(MemWrite, MemRead, Address, Wdata, inst, Rdata, outAddress, outInst);
	input	MemWrite, MemRead;
	input	[31:0]	Address, Wdata;
	input	[4:0]	inst;
	output	[31:0]	Rdata, outAddress;
	output	[4:0]	outInst;
	
	reg	[4:0]	outInst;
	reg	[31:0]	Rdata, outAddress;
	reg	[31:0] mem[31:0];
	
	initial begin
		mem[20] = 32'h0000001C;
		mem[21] = 32'h00000036;
		mem[22] = 32'h00000040;
		mem[23] = 32'h000000A9;
	end
	
	always @(1) begin
			if (MemWrite == 1'b1)				//Write mode
				mem[Address[6:0]] = Wdata[31:0];				
			else if (MemRead == 1'b1) begin
				Rdata = mem[Address[6:0]];
				outAddress = Address;
				outInst = inst;
			end
			else if(MemWrite == 1'b0 && MemRead == 1'b0) begin
				outAddress = Address;
				outInst = inst;
			end
	end
endmodule
